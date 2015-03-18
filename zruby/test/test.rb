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


mUser.User.attributes(false).each { |attrib|
  puts "#{attrib.name} = #{attrib}"
}

mUser.User.attributes(false).each { |attrib|
  puts "#{attrib.name} = #{attrib}"
}
