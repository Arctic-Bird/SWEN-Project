// {Name: TU Food Trucks}
// {Description: AI assistant for the TU Food Trucks Android app.}

// Use this sample to create your own voice/text commands

title("Food Recommendations")

intent('Food recommendations',
        reply("What would you like? Options are: Vegetarian, Meat, Seafood, or Sides"))
       
intent('(I would like|I want|) Vegetarian (food)',
        reply("I (recommend|think you would like) patatas bravas"))


intent("(I would like|want|) (food containting|with|) Meat",
        reply("You would (like|enjoy) (loco moco|sausage pizza|pepperoni pizza|Hawaiian pizza)"))


intent("(I would like|want|) (to eat|to have|) Seafood",
        reply("Sorry, we are out of seafood options for now. Check back (later|in a few days!)"))


intent("(I just|only|) (want|would like|) Sides",
        reply("(Can't go wrong with|I think you would enjoy) fried rice!"))

intent('(Do you have any|) Beverage recommendations')
        reply("You would (like|enjoy) Tea Time! They (serve|have) boba, kombucha, and iced tea!");




// Give your AI assistant some knowledge about the world
corpus(`
    Hello, I'm Alan.
    I am your virtual assistant with the TU Food Trucks Android app!
    If you are in need of food and/or beverage recommendations, let me know and I will happily assist you!
    Current menus are limited, but the food trucks connected to TU Food Trucks serve: sausage pizza, pepperoni pizza, Hawaiian pizza, boba, kombucha, iced tea, patatas bravas, fried rice, loco moco, and elotes.
    Thank you for using TU Food Trucks.
`);
