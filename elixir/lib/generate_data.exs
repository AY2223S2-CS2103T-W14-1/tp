#!/usr/bin/env elixir

# Import the JsonGenerator module
import JsonGenerator

# Call generate_json/1 with the desired number of rows
json_data = generate_json(1000)

# Print the JSON data to the console
IO.puts(json_data)
