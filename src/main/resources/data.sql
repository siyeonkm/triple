insert into city(name, created_date)
values ('삿포로', '2023-03-12 16:23:03'),
('도쿄', '2023-03-10 19:51:03'),
('오사카', '2022-03-24 18:35:03'),
('서울', '2023-03-13 20:45:35'),
('부산', '2023-03-01 13:14:13'),
('제주', '2023-01-12 22:34:03'),
('상하이', '2023-02-20 21:14:03'),
('베이징', '2023-02-27 12:59:43'),
('태국', '2023-01-13 07:14:03'),
('말레이시아', '2023-02-08 11:54:32'),
('발리', '2023-01-23 04:14:03'),
('이탈리아', '2022-03-16 05:34:03'),
('프랑스', '2022-03-12 09:14:03'),
('스위스', '2022-01-02 03:45:03'),
('오스트리아', '2023-02-21 08:10:03');

insert into trip(title, city_id, start_date, end_date)
values('일본여행', 1, '2023-03-14', '2023-03-27'),
('일본가좌', 1, '2023-03-19', '2023-03-28'),
('국내여행', 5, '2023-03-12', '2023-03-30'),
('한국조져', 4, '2023-05-01', '2023-05-04'),
('힐링여행', 10, '2023-06-02', '2023-06-17'),
('힐링여행', 10, '2024-06-02', '2024-06-17'),
('유럽가자', 14, '2023-07-10', '2023-08-12'),
('유럽가자', 13, '2023-07-11', '2023-08-12'),
('유럽가자', 14, '2023-10-12', '2023-10-22');

insert into member(nickname)
values('와앙'),
('가취가욥');

insert into tripper(member_id, trip_id)
values(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(2, 2),
(2, 4);

insert into history(member_id, city_id, viewed_date)
values(1, 1, '2023-03-16 12:23:32'),
      (1, 5, '2023-03-09 23:23:43'),
      (1, 10, '2023-03-12 11:23:53'),
      (2, 3, '2023-03-15 21:43:21'),
      (1, 12, '2023-03-17 20:37:22');