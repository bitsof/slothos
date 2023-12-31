import sys
import requests
import json
import sqlite3
import inflect

# Generates initial json files from raw data from the exercise repo
def create_initial_json_files():
    # URL of the JSON data
    url = "https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/dist/exercises.json"

    # Make a GET request to fetch the raw JSON content
    response = requests.get(url)
    response.raise_for_status()  # Raise an exception for HTTP errors

    # Parse the JSON
    raw_exercises = json.loads(response.text)

    print(raw_exercises[0])
    return

# Function to insert data into table
def insert_data(cursor, table_name, data):
    # Assuming each dictionary in 'data' represents a row
    for entry in data:
        columns = ', '.join(entry.keys())
        placeholders = ', '.join('?' * len(entry))
        sql = f"INSERT INTO {table_name} ({columns}) VALUES ({placeholders})"
        print(sql)
        # cursor.execute(sql, tuple(entry.values()))

def insert_json_data():
    json_path = '../assets/json/'


# Creates json files from sqlite database
def create_json_data():
    return

# Empties database
def empty_database():
    return

# Lists commands

def help():
    return

def insert_categories():
    conn = sqlite3.connect('workout_database.db')
    cursor = conn.cursor()
    json_path = '../app/src/main/assets/json/categories.json'
    with open(json_path, 'r') as file:
        data = json.load(file)

    for item in data:
        cursor.execute("INSERT INTO categories (name, description) VALUES (?, ?)",
                   (item['name'], item['description']))
    conn.commit()
    conn.close()

def insert_equipment():
    conn = sqlite3.connect('workout_database.db')
    cursor = conn.cursor()
    json_path = '../app/src/main/assets/json/equipment.json'
    with open(json_path, 'r') as file:
        equipment_data = json.load(file)

    for item in equipment_data:
        cursor.execute("INSERT INTO equipment (name, description) VALUES (?, ?)",
                   (item['name'], item['description']))
    conn.commit()
    conn.close()

def insert_forces():
    conn = sqlite3.connect('workout_database.db')
    cursor = conn.cursor()
    json_path = '../app/src/main/assets/json/forces.json'
    with open(json_path, 'r') as file:
        data = json.load(file)

    for item in data:
        cursor.execute("INSERT INTO forces (name, description) VALUES (?, ?)",
                   (item['name'], item['description']))
    conn.commit()
    conn.close()

def insert_levels():
    conn = sqlite3.connect('workout_database.db')
    cursor = conn.cursor()
    json_path = '../app/src/main/assets/json/levels.json'
    with open(json_path, 'r') as file:
        data = json.load(file)

    for item in data:
        cursor.execute("INSERT INTO levels (name) VALUES (?)",
                   (item['name'],))
    conn.commit()
    conn.close()

def insert_mechanics():
    conn = sqlite3.connect('workout_database.db')
    cursor = conn.cursor()
    json_path = '../app/src/main/assets/json/mechanics.json'
    with open(json_path, 'r') as file:
        data = json.load(file)

    for item in data:
        cursor.execute("INSERT INTO mechanics (name, description) VALUES (?, ?)",
                   (item['name'], item['description']))
    conn.commit()
    conn.close()

def insert_muscles():
    conn = sqlite3.connect('workout_database.db')
    cursor = conn.cursor()
    json_path = '../app/src/main/assets/json/muscles.json'
    with open(json_path, 'r') as file:
        data = json.load(file)

    for item in data:
        cursor.execute("INSERT INTO muscles (name, description) VALUES (?, ?)",
                   (item['name'], item['description']))
    conn.commit()
    conn.close()

def get_or_insert_id(cursor, table, column, value):
    if value == None:
        return None
    cursor.execute(f"SELECT id FROM {table} WHERE {column} = ?", (value,))
    row = cursor.fetchone()
    if row:
        return row['id']
    else:
        print("Attemptint to insert: ", value)
        cursor.execute(f"INSERT INTO {table} (name, description) VALUES (?, ?)", (value, ""))
        return cursor.lastrowid
    
def get_or_insert_id_exercise(cursor, table, column, exercise):
    cursor.execute(f"SELECT id FROM {table} WHERE {column} = ?", (exercise['id'],))
    row = cursor.fetchone()
    if row:
        return row['id']
    else:
        print("Attempting to insert: ", exercise['id'])
        instructions = " ".join(exercise['instructions'])
        print(f"INSERT INTO {table} (name_id, name, instructions) VALUES (?, ?, ?)", (exercise['id'], exercise['name'], instructions))
        cursor.execute(f"INSERT INTO {table} (name_id, name, instructions) VALUES (?, ?, ?)", (exercise['id'], exercise['name'], instructions))
        return cursor.lastrowid

# attribute refers to json field, defined here https://github.com/yuhonas/free-exercise-db
def insert_foreign_keys(conn, exercise, table, attribute, fk_table):
    cursor = conn.cursor()
    exercise_id = get_or_insert_id_exercise(cursor, "exercises", "name_id", exercise)
    
    p = inflect.engine()
    table_singular = p.singular_noun(table) or table

    # Check if the attribute is a list or a single string
    # If it's a string, convert it into a list with a single element
    items = exercise[attribute] if isinstance(exercise[attribute], list) else [exercise[attribute]]
    for item in items:
        related_id = get_or_insert_id(cursor, table, "name", item)
        if related_id == None:
            continue
        cursor.execute(f"SELECT * FROM {fk_table} WHERE exercise_id = ? AND {table_singular}_id = ?", (exercise_id, related_id))
        if not cursor.fetchone():
            cursor.execute(f"INSERT INTO {fk_table} (exercise_id, {table_singular}_id) VALUES (?, ?)", (exercise_id, related_id))

def insert_all_foreign_keys():
    try:
        conn = sqlite3.connect('workout_database.db')
        conn.row_factory = sqlite3.Row

        url = "https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/dist/exercises.json"
        response = requests.get(url)
        response.raise_for_status()

        raw_exercises = json.loads(response.text)
        for exercise in raw_exercises:
            print("Exercise:", exercise)
            # if 'category' in exercise:
            #     insert_foreign_keys(conn, exercise, "categories", "category", "exercise_category_fks")
            # if 'equipment' in exercise:
            #     insert_foreign_keys(conn, exercise, "equipment", "equipment", "exercise_equipment_fks")
            # if 'primaryMuscles' in exercise:
            #     insert_foreign_keys(conn, exercise, "muscles", "primaryMuscles", "exercise_primary_muscle_fks")
            # if 'secondaryMuscles' in exercise:
            #     insert_foreign_keys(conn, exercise, "muscles", "secondaryMuscles", "exercise_secondary_muscle_fks")
            # ... repeat for other attributes
            # if 'force' in exercise:
            #     insert_foreign_keys(conn, exercise, "forces", "force", "exercise_force_fks")
            # if 'level' in exercise:
            #     insert_foreign_keys(conn, exercise, "levels", "level", "exercise_level_fks")
            # if 'mechanic' in exercise:
            #     insert_foreign_keys(conn, exercise, "mechanics", "mechanic", "exercise_mechanic_fks")
            

        conn.commit()
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        conn.close()

# Makes it possible to run functions on command line e.g. >python mockups.py delete_all
if __name__ == '__main__':
    if len(sys.argv) == 2:
        globals()[sys.argv[1]]()
    elif len(sys.argv) == 3:
        globals()[sys.argv[1]](sys.argv[2])