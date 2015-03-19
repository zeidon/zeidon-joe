# A sample Ruby script that loads a Zeidon application.

load 'zeidon.rb'

# Load the OE and create a task
oe = Zeidon.get_object_engine
task = oe.ZENCAs.create_task
puts task.task_id

# Perform an activate.
mUser = task.activate "mUser", :qual => ["ID", 3]

puts "ID = #{mUser.User.ID}"

# Try changing an attribute.
puts "email = #{mUser.User.eMailUserName }"
mUser.User.eMailUserName = "test@email.com"
puts "email = #{mUser.User.eMailUserName }"

# Loop through each of the attributes in the User entity.
mUser.User.each_attrib(:include_hidden => false, :include_null => false) { |attrib|
  puts "#{attrib.name} = #{attrib}"
}

# Loop through all Activity entities.
mUser.Activity.each {
  puts "Activity.ID = #{mUser.Activity.ID}"
}

# Another loop but this time we'll use the EntityInstance
mUser.Activity.each { |ei|
  puts "Activity.ID = #{ei.ID}"
}

mUser.logObjectInstance
