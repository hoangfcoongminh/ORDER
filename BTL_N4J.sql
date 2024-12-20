
-- drop database n4j_database; 

create database if not exists n4j_database;
use n4j_database;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone_number` varchar(13) DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `detail` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `fieldtype` (
  `fieldtype_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `detail` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`fieldtype_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `field` (
  `field_id` int NOT NULL AUTO_INCREMENT,
  `field_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `field_image` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fieldtype_id` int DEFAULT NULL,
  PRIMARY KEY (`field_id`),
  KEY `fieldtype_id_idx` (`fieldtype_id`),
  CONSTRAINT `fieldtype_id` FOREIGN KEY (`fieldtype_id`) REFERENCES `fieldtype` (`fieldtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `timeslot` (
  `time_slot_id` int NOT NULL AUTO_INCREMENT,
  `price_per_hour` int DEFAULT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`time_slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `field_timeslot` (
  `field_timeslot_id` int NOT NULL AUTO_INCREMENT,
  `available` tinyint DEFAULT NULL,
  `field_id` int NOT NULL,
  `time_slot_id` int NOT NULL,
  PRIMARY KEY (`field_timeslot_id`),
  KEY `field_id_idx` (`field_id`),
  KEY `timeSlot_id_idx` (`time_slot_id`),
  CONSTRAINT `field_id` FOREIGN KEY (`field_id`) REFERENCES `field` (`field_id`),
  CONSTRAINT `time_slot_id` FOREIGN KEY (`time_slot_id`) REFERENCES `timeslot` (`time_slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `note` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `field_timeslot_id` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `field_timeslot_id_idx` (`field_timeslot_id`),
  CONSTRAINT `field_timeslot_id` FOREIGN KEY (`field_timeslot_id`) REFERENCES `field_timeslot` (`field_timeslot_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user(username, password, full_name, gender, dob, phone_number, email, role) VALUES('user1', '$2a$10$bFQBIzt9hb2htOQsZusib.veReqAxQP8hlGGBAMeOyAutyyVDMtpC', N'User 01', N'Nam', '2024-12-05', '00000000000', 'user012@gmail.com', 'CUSTOMER');
INSERT INTO user(username, password, full_name, gender, dob, phone_number, email, role) VALUES('user2', '$2a$10$bFQBIzt9hb2htOQsZusib.veReqAxQP8hlGGBAMeOyAutyyVDMtpC', N'User 02', N'Nữ', '2024-12-01', '00000000001', 'user022@gmail.com', 'CUSTOMER');
INSERT INTO user(username, password, full_name, gender, dob, phone_number, email, role) VALUES('admin1','$2a$10$bFQBIzt9hb2htOQsZusib.veReqAxQP8hlGGBAMeOyAutyyVDMtpC', N'Admin 01', N'Nữ', '2024-12-06', '00000000002', 'admin012@gmail.com', 'ADMIN');

INSERT INTO category(category_name, detail) VALUES(N'Loại sân', N'');
INSERT INTO category(category_name, detail) VALUES(N'Sân', N'');
INSERT INTO category(category_name, detail) VALUES(N'Liên hệ', N'');
INSERT INTO category(category_name, detail) VALUES(N'Giới thiệu', N'');

INSERT INTO fieldtype(type_name, detail, category_id) VALUES(N'Sân bóng 7', N'Sân bóng đá cho 7 người', 1);
INSERT INTO fieldtype(type_name, detail, category_id) VALUES(N'Sân bóng 11', N'Sân bóng đá cho 11 người', 1);
INSERT INTO fieldtype(type_name, detail, category_id) VALUES(N'Sân Tennis', N'Sân Tennis chất lượng cao', 1);
INSERT INTO fieldtype(type_name, detail, category_id) VALUES(N'Sân Bóng chuyền', N'Sân Bóng chuyền đạt chuẩn quốc tế', 1);
INSERT INTO fieldtype(type_name, detail, category_id) VALUES(N'Sân Bóng rổ', N'Sân chuyên nghiệp', 1);

INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Cầu Giấy', '1.jpg', N'165 Cầu Giấy, Cầu Giấy, Hà Nội', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Hoàng Mai','2.jpg', N'Số 3, Linh Đàm, Hoàng Mai', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Đông Anh','3.jpg', N'Ngoại Giao Đoàn, Bắc Từ Liêm', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Hà Đông','4.jpg', N'Số 46 Xuân Đỉnh, Tây Hồ', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Hiệp Hòa','5.jpg', N'35 Pháo Đài Láng, Đống Đa', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Lan Thuần','6.jpg', N'300 Trưng Nữ Vương, Đà Nẵng', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Đông Ngạc','7.jpg', N'98 Tiêu La, Đà Nẵng', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Thái Thịnh','8.jpg', N'Quận 1, TP. Hồ Chí Minh', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Trung Hòa','9.jpg', N'Quận 7, TP. Hồ Chí Minh', 1);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 7 - Đống Đa','10.jpg', N'23 Tô Ngọc Vân, Tây Hồ', 1);

INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Cầu Giấy', '11.jpg', N'Quận 1, TP. Hồ Chí Minh', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Hoàng Mai','12.jpg', N'Số 3, Linh Đàm, Hoàng Mai', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Sóc Sơn','13.jpg', N'Ngoại Giao Đoàn, Bắc Từ Liêm', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Hà Đông','14.jpg', N'Số 46 Xuân Đỉnh, Tây Hồ', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Hiệp Hòa','15.jpg', N'35 Pháo Đài Láng, Đống Đa', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Lan Thuần','16.jpg', N'165 Cầu Giấy, Cầu Giấy, Hà Nội', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Đông Ngạc','17.jpg', N'98 Tiêu La, Đà Nẵng', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Min','18.jpg', N'Số 46 Xuân Đỉnh, Tây Hồ', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Yên Phong','19.jpg', N'Quận 11, TP. Hồ Chí Minh', 2);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân 11 - Phú Nhuận','20.jpg', N'23 Tô Ngọc Vân, Tây Hồ', 2);

INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Bạch Mai', '21.jpg', N'Quận 1, TP. Hồ Chí Minh', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Hoàng Gia','22.jpg', N'Số 3, Linh Đàm, Hoàng Mai', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Gia Hưng','23.jpg', N'Ngoại Giao Đoàn, Bắc Từ Liêm', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - 369','24.jpg', N'Nam Sách, Hải Dương', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Hoa Hồng','25.jpg', N'Ninh Giang, Hải Dương', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Thùy Linh','26.jpg', N'Kinh Môn, Hải Dương', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Nguyễn Hoàng','27.jpg', N'98 Tiêu La, Đà Nẵng', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - Sơn Đòng','28.jpg', N'Số 46 Xuân Đỉnh, Tây Hồ', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis - VIP','29.jpg', N'Quận 9, TP. Hồ Chí Minh', 3);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Tennis','30.jpg', N'Quảng An, Tây Hồ', 3);

INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền số 1', '31.jpg', N'Quận 1, TP. Hồ Chí Minh', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền VIP','32.jpg', N'Số 3, Linh Đàm, Hoàng Mai', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền VIP2','33.jpg', N'Ngoại Giao Đoàn, Bắc Từ Liêm', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền - 369','34.jpg', N'Nam Sách, Hải Dương', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền VIP3','35.jpg', N'Ninh Giang, Hải Dương', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền Thụy Lạc','36.jpg', N'Kinh Môn, Hải Dương', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền rẻ','37.jpg', N'98 Tiêu La, Đà Nẵng', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền CLC','38.jpg', N'Số 46 Xuân Đỉnh, Tây Hồ', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền - VIPVIP','39.jpg', N'Quận 9, TP. Hồ Chí Minh', 4);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng chuyền hợp ví','40.jpg', N'Quảng An, Tây Hồ', 4);

INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ nhà thể chất', '41.jpg', N'Quận 1, TP. Hồ Chí Minh', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ chuẩn','42.jpg', N'Số 3, Linh Đàm, Hoàng Mai', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ size nhỏ','43.jpg', N'Ngoại Giao Đoàn, Bắc Từ Liêm', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ size lớn','44.jpg', N'Nam Sách, Hải Dương', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ chuẩn thi đấu','45.jpg', N'Ninh Giang, Hải Dương', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ chuẩn NBA','46.jpg', N'Kinh Môn, Hải Dương', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ NBV','47.jpg', N'98 Tiêu La, Đà Nẵng', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ VIP1','48.jpg', N'Số 46 Xuân Đỉnh, Tây Hồ', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ VIP2','49.jpg', N'Quận 9, TP. Hồ Chí Minh', 5);
INSERT INTO field(field_name, field_image, address, fieldtype_id) VALUES(N'Sân Bóng rổ VIP3','50.jpg', N'Quảng An, Tây Hồ', 5);

INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(350000, '6:00', '8:00');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(400000, '8:00', '10:00');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(400000, '9:00', '11:00');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(350000, '13:00', '15:00');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(400000, '13:30', '15:30');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(450000, '14:30', '16:30');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(500000, '17:45', '19:45');
INSERT INTO timeslot(price_per_hour, start_time, end_time) VALUES(500000, '18:00', '20:00');

INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,1,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,1,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,1,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,1,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,1,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,1,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,2,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,2,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,2,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,2,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,2,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,2,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,3,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,3,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,4,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,4,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,4,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,4,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,5,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,5,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,5,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,6,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,6,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,6,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,6,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,6,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,6,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,7,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,7,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,7,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,8,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,8,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,8,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,8,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,8,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,8,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,9,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,9,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,9,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,9,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,9,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,10,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,10,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,11,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,11,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,11,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,11,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,11,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,11,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,12,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,12,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,12,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,12,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,12,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,13,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,13,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,13,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,14,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,14,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,14,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,14,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,15,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,15,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,15,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,15,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,16,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,16,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,17,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,17,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,17,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,18,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,18,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,18,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,19,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,19,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,19,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,19,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,19,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,20,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,20,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,20,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,20,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,21,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,21,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,22,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,23,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,23,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,23,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,23,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,23,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,23,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,24,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,24,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,24,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,24,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,24,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,25,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,25,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,25,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,25,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,25,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,26,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,26,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,26,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,27,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,27,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,27,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,27,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,27,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,28,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,28,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,28,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,28,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,29,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,30,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,30,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,31,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,31,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,31,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,31,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,31,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,32,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,32,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,33,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,33,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,33,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,34,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,34,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,34,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,35,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,35,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,35,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,35,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,35,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,35,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,36,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,36,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,36,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,36,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,36,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,36,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,37,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,37,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,37,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,37,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,38,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,38,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,38,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,38,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,39,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,39,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,39,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,39,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,39,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,39,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,40,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,40,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,41,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,41,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,42,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,43,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,43,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,43,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,43,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,43,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,44,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,44,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,44,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,44,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,45,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,45,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,45,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,45,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,45,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,46,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,46,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,46,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,46,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,47,2);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,47,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,47,7);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,47,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,48,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,48,3);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,48,4);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,48,8);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,49,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,49,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,49,6);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,50,1);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,50,5);
INSERT INTO field_timeslot(available, field_id, time_slot_id) VALUES(1,50,6);

SELECT * FROM user;
SELECT * FROM category;
SELECT * FROM fieldtype;
SELECT * FROM field;
SELECT * FROM timeslot;
SELECT * FROM field_timeslot;
SELECT * FROM booking;
