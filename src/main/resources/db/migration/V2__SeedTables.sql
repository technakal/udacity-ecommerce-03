insert into products (name, description, price, quantity)
values 
('Pet Hair Remover', 'Removes pet hair faster than you can call the local shelter.', 14.99, 10),
('Stereo Headphones', 'Headphones, made for stereo.', 54.49, 5),
('Regular Old Banana', 'Just a banana. Not old, but regular for sure.', 0.99, 15),
('Baby Bonnet', 'I think it is a hat for a baby.', 7.59, 2);

insert into reviews (username, rating, review_text, product_id)
values 
('john c.', 4, 'This saved my clothes from my shedding cat. Wish it came in other colors, though.', 1),
('raiden_lives', 3, 'Pet hair was always a problem. Not anymore! I only give three star ratings, though.', 1),
('mad_cat', 1, 'I spent a lot of time putting my hair on everything. These just remove it? Not cool.', 1),
('Xxuser2017xX', 5, 'These headphones do the job.', 2),
('Ben Nanner', 3, 'It really is just a banana.', 3),
('john c.', 2, 'Not sure what I was expecting. I do not even have a baby, so why did I buy this?', 4),
('Carly Sanders', 5, 'Perfect bonnet for my perfect baby.', 4);

insert into comments (username, comment_text, review_id)
values
('t-pose', 'Thank you! Your review helped make up my mind!', 1),
('gTroll', 'John is a baby. Buy him a Baby Bonnet.', 1),
('smalley_b', 'Bad kitty!', 3),
('Bonnie Nerr', 'What did you expect, Ben. It literally says it is just a banana', 5);