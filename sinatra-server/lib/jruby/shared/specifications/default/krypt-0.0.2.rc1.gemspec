# -*- encoding: utf-8 -*-
# stub: krypt 0.0.2.rc1 ruby lib

Gem::Specification.new do |s|
  s.name = "krypt"
  s.version = "0.0.2.rc1"

  s.required_rubygems_version = Gem::Requirement.new("> 1.3.1") if s.respond_to? :required_rubygems_version=
  s.authors = ["Hiroshi Nakamura, Martin Bosslet"]
  s.date = "2014-02-23"
  s.description = "krypt provides a unified framework for Ruby cryptography by offering a platform- and library-independent provider mechanism."
  s.email = "Martin.Bosslet@gmail.com"
  s.extra_rdoc_files = ["README.md"]
  s.files = ["README.md"]
  s.homepage = "https://github.com/krypt/krypt"
  s.licenses = ["MIT"]
  s.require_paths = ["lib"]
  s.required_ruby_version = Gem::Requirement.new(">= 1.9.3")
  s.rubygems_version = "2.1.9"
  s.summary = "Platform- and library-independent cryptography for Ruby"

  if s.respond_to? :specification_version then
    s.specification_version = 4

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<ffi>, [">= 0"])
      s.add_runtime_dependency(%q<krypt-core>, ["= 0.0.2.rc1"])
    else
      s.add_dependency(%q<ffi>, [">= 0"])
      s.add_dependency(%q<krypt-core>, ["= 0.0.2.rc1"])
    end
  else
    s.add_dependency(%q<ffi>, [">= 0"])
    s.add_dependency(%q<krypt-core>, ["= 0.0.2.rc1"])
  end
end
