INSERT INTO person (name) VALUES ('John');
INSERT INTO person (name) VALUES ('Jane');
INSERT INTO person (name) VALUES ('Alice');
INSERT INTO person (name) VALUES ('Heidi');
INSERT INTO person (name) VALUES ('Bob');
INSERT INTO person (name) VALUES ('Stuart');
INSERT INTO person (name) VALUES ('Madeline');

INSERT INTO tweet (person_id, text) VALUES (1, 'This is some sample text');
INSERT INTO tweet (person_id, text) VALUES (1, 'Happy birthday to my brother @MOTIofficial');
INSERT INTO tweet (person_id, text) VALUES (2, 'Our @ultra Miami 2016 set is online now!! https://youtu.be/Q-nAyODfgGE');
INSERT INTO tweet (person_id, text) VALUES (2, 'It\'s a paws-itively beautiful day. #NationalPuppyDay');
INSERT INTO tweet (person_id, text) VALUES (2, 'Yesterday President Obama revealed his March Madness bracket. Check it out: http://ofa.bo/g9zF  #Baracketology');

INSERT INTO personfollower (follower, following) VALUES (1, 2);
INSERT INTO personfollower (follower, following) VALUES (2, 3);
INSERT INTO personfollower (follower, following) VALUES (3, 1);

