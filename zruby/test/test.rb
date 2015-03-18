load 'zeidon.rb'

oe = Zeidon.get_object_engine
task = oe.ZENCAs.create_task
puts task.task_id

mUser = task.activate "mUser", :qual => ["ID", 3]

puts "ID = #{mUser.User.ID}"
puts mUser.User.ID + 3
puts 4 + mUser.User.ID
puts "email = #{mUser.User.eMailUserName }"
email = mUser.User.eMailUserName
mUser.User.eMailUserName = "test@email.com"
puts "email class = #{email.class}"
puts "email = #{email.getString }"


mUser.User.attributes(:include_hidden => false).each { |attrib|
  puts "#{attrib.name} = #{attrib}"
}

puts "-----------"

mUser.User.each_attrib(:include_hidden => false, :include_null => false) { |attrib|
  puts "#{attrib.name} = #{attrib}"
}

mUser.Activity.each {
  puts "Activity.ID = #{mUser.Activity.ID}"
}

mUser.Activity.each { |attr|
  puts "Activity.ID = #{attr.ID}"
}

#mUser.logObjectInstance
