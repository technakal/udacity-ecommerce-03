var conn = new Mongo();
db = conn.getDB('ecommerce');

var doc1 = {
  userName: 'john c.',
  rating: 4,
  reviewText:
    'This saved my clothes from my shedding cat. Wish it came in other colors, though.',
  productId: 1,
  comments: [
    {
      id: 1,
      userName: 't-pose',
      commentText: 'Thank you! Your review helped make up my mind!',
    },
    {
      id: 2,
      userName: 'gTroll',
      commentText: 'John is a baby. Buy him a Baby Bonnet.',
    },
  ],
};
var doc2 = {
  userName: 'raiden_lives',
  rating: 3,
  reviewText:
    'Pet hair was always a problem. Not anymore! I only give three star ratings, though.',
  productId: 1,
  comments: [],
};

var doc3 = {
  userName: 'mad_cat',
  rating: 1,
  reviewText:
    'I spent a lot of time putting my hair on everything. These just remove it? Not cool.',
  productId: 1,
  comments: [
    {
      id: 1,
      userName: 'smalley_b',
      commentText: 'Bad kitty!',
    },
  ],
};
var doc4 = {
  userName: 'Xxuser2017xX',
  rating: 5,
  reviewText: 'These headphones do the job.',
  productId: 2,
  comments: [],
};
var doc5 = {
  userName: 'Ben Nanner',
  rating: 3,
  reviewText: 'It really is just a banana.',
  productId: 3,
  comments: [
    {
      id: '1',
      userName: 'Bonnie Nerr',
      commentText:
        'What did you expect, Ben. It literally says it is just a banana',
    },
  ],
};
var doc6 = {
  userName: 'john c.',
  rating: 2,
  reviewText:
    'Not sure what I was expecting. I do not even have a baby, so why did I buy this?',
  productId: 4,
  comments: [],
};
var doc7 = {
  userName: 'Carly Sanders',
  rating: 5,
  reviewText: 'Perfect bonnet for my perfect baby.',
  productId: 4,
  comments: [],
};
db.reviews.insert([doc1, doc2, doc3, doc4, doc5, doc6, doc7]);
