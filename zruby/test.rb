load 'zeidon.rb'

oe = Zeidon.get_object_engine
task = oe.EnsvCITV.create_task
puts task.task_id

carrier = task.activate "Carrier", :qual => ["Id", 1]
puts carrier.to_s
carrier.logObjectInstance
