defmodule MyProject.Mixfile do
  use Mix.Project

  def project do
    [
      app: :my_project,
      version: "0.1.0",
      elixir: "~> 1.14",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  
  def deps do
    [
      {:faker, "~> 0.17"},
      {:poison, "~> 4.0"}    
    ]
  end
end