# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = "backports"
  s.version = "2.7.1"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["Marc-Andr\303\251 Lafortune"]
  s.date = "2013-01-21"
  s.description = "Essential backports that enable some of the really nice features of ruby 1.8.7, ruby 1.9 and rails from ruby 1.8.6 and earlier."
  s.email = ["github@marc-andre.ca"]
  s.homepage = "http://github.com/marcandre/backports"
  s.require_paths = ["lib"]
  s.rubygems_version = "1.8.24"
  s.summary = "Backports of Ruby features for older ruby."

  if s.respond_to? :specification_version then
    s.specification_version = 3

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
    else
    end
  else
  end
end
