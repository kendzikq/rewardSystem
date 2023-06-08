-- PREDEFINED USERS
INSERT INTO REWARD_USER (ID, NAME)
VALUES (1, 'David'),
       (2, 'Emily'),
       (3, 'Victor');

-- PREDEFINED PURCHASES
INSERT INTO PURCHASE (ID, ITEM, AMOUNT, DATE, USER_ID)
VALUES (1, 'FRIDGE', 40, '2023-01-05', 1),
       (2, 'TV', 70, '2023-01-12', 1),
       (3, 'FRIDGE', 2, '2023-02-07', 2),
       (4, 'TV', 53, '2023-02-15', 2),
       (5, 'WASHING_MACHINE', 120, '2023-02-17', 3),
       (6, 'TV', 13, '2023-02-21', 3),
       (7, 'FRIDGE', 300, '2023-02-16', 3),
       (8, 'TV', 101, '2023-03-16', 3),
       (9, 'WASHING_MACHINE', 100, '2023-03-16', 3),
       (10, 'FRIDGE', 99, '2023-03-16', 3);
