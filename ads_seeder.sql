use adlister_db;


INSERT INTO users (username, email, password, phoneNumber)
VALUES ('Bobby Bob', 'bobby@email.com', '$2a$12$hMBHftmWPtdIWrgX1My8Wuhz9wrZULG5MrqmoVwCSBH24t/DDlPNS', '1234567898'),
       ('Sally Smith', 'sally@email.com', '$2a$12$hMBHftmWPtdIWrgX1My8Wuhz9wrZULG5MrqmoVwCSBH24t/DDlPNS', '9876543212'),
       ('Karen Schultz', 'karen@email.com', '$2a$12$hMBHftmWPtdIWrgX1My8Wuhz9wrZULG5MrqmoVwCSBH24t/DDlPNS', '4536572985'),
       ('Fred White', 'fred@email.com', '$2a$12$hMBHftmWPtdIWrgX1My8Wuhz9wrZULG5MrqmoVwCSBH24t/DDlPNS', '7658973657'),
       ('Bud Gibson', 'bud@email.com', '$2a$12$hMBHftmWPtdIWrgX1My8Wuhz9wrZULG5MrqmoVwCSBH24t/DDlPNS', '8734567823');
INSERT INTO ads (user_id, title, description)
VALUES
(1, 'plumbing', 'I am in need of a plumber.'),
(2, 'Carpenter', 'If you need a carpenter, please let me know.'),
(3, 'painting', 'I am looking for someone to paint my fence, please let me know if you are intrested'),
(4, 'Painter', 'I am a painter looking for any work needed.'),
(5, 'electrician', 'I am a electrician looking for any work needed.');




SELECT username, adlister_db.ads.title
FROM users
Left JOIN ads ON users.id = ads.user_id WHERE user_id = 5;


