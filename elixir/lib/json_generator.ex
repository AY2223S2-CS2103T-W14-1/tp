defmodule JsonGenerator do
  def generate_clients(num_rows) do
    Faker.start()

    emails = Faker.Util.sample_uniq(num_rows, &Faker.Internet.email/0)
    
    Enum.map(1..num_rows, fn i ->
      %{
        name: Faker.String.naughty() <> "foo", # to ensure non empty
        email: Enum.fetch!(emails, i - 1),
        source: Faker.String.naughty() <> "foo",
        entityName: "Client",
        yearOfBirth: to_string(:rand.uniform(9999)) |> String.pad_leading(4, "0"),
        mobileNumber: Faker.Phone.PtPt.cell_number()
      }
    end)
  end

  
  def generate_projects(num_rows) do
    Faker.start()

    names = Faker.Util.sample_uniq(num_rows, &Faker.String.base64/0)
    
    Enum.map(1..num_rows, fn i ->
      %{
        name: Enum.fetch!(names, i - 1) <> "foo", # to ensure non empty
        status: Enum.fetch!(Enum.take_random(["NOT_STARTED", "IN_PROGRESS", "DONE"], 1), 0),
        clientEmail: Faker.Internet.email(),
        source: Faker.String.naughty() <> "foo",
        description: Faker.String.naughty(),
        acceptedOn: Calendar.strftime(Faker.Date.between(~D[2010-12-10], ~D[2016-12-25]), "%d/%m/%Y"),
        deadline: Calendar.strftime(Faker.Date.between(~D[2010-12-10], ~D[2016-12-25]), "%d/%m/%Y"),
        entityName: "Project"
      }
    end)
  end

  def generate_json(num_rows) do
    clients = generate_clients(num_rows)
    projects = generate_projects(num_rows)
    Poison.encode!(%{"clients" => clients, "projects" => projects})
  end
end
