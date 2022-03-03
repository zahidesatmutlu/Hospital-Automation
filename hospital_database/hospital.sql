-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.22-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table hospital.appointment
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(50) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(50) NOT NULL,
  `appointment_date` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table hospital.appointment: ~3 rows (approximately)
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` (`id`, `doctor_id`, `doctor_name`, `patient_id`, `patient_name`, `appointment_date`) VALUES
	(11, 3, 'Zeynep Nida Mutlu', 4, 'Elif Mutlu', '2022-52-04 14:00:00'),
	(12, 12, 'Hasan Atıcı', 4, 'Elif Mutlu', '2022-00-04 15:00:00');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;

-- Dumping structure for table hospital.clinic
CREATE TABLE IF NOT EXISTS `clinic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table hospital.clinic: ~9 rows (approximately)
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` (`id`, `name`) VALUES
	(1, 'Cildiye'),
	(2, 'Göz Hastalıkları'),
	(3, 'Psikiyatri'),
	(4, 'Pediatri'),
	(7, 'Ortopedi'),
	(8, 'Nöroloji'),
	(9, 'Nefroloji'),
	(11, 'Genel Cerrahi');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;

-- Dumping structure for table hospital.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tcno` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` enum('patient','doctor','chiefphysician') DEFAULT 'patient',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table hospital.user: ~3 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `tcno`, `password`, `name`, `type`) VALUES
	(1, '123', '123', 'Zahid Esat Mutlu', 'chiefphysician'),
	(3, '1234', '1234', 'Zeynep Nida Mutlu', 'doctor'),
	(4, '123456', '123456', 'Elif Mutlu', 'patient'),
	(12, '2345', '2345', 'Hasan Atıcı', 'doctor'),
	(13, '3456', '3456', 'Betül Karagöz', 'doctor'),
	(14, '4567', '4567', 'Furkan Koyuncu', 'doctor'),
	(15, '5678', '5678', 'Uğur Kaval', 'doctor'),
	(16, '6789', '6789', 'Burak Balkı', 'doctor');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table hospital.worker
CREATE TABLE IF NOT EXISTS `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table hospital.worker: ~8 rows (approximately)
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` (`id`, `clinic_id`, `user_id`) VALUES
	(17, 2, 3),
	(18, 3, 3),
	(25, 1, 12),
	(26, 4, 13),
	(27, 7, 14),
	(28, 8, 15),
	(29, 9, 16),
	(30, 11, 12);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;

-- Dumping structure for table hospital.workhour
CREATE TABLE IF NOT EXISTS `workhour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(50) NOT NULL DEFAULT '',
  `workdate` varchar(50) NOT NULL DEFAULT '',
  `status` enum('A','P') NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table hospital.workhour: ~8 rows (approximately)
/*!40000 ALTER TABLE `workhour` DISABLE KEYS */;
INSERT INTO `workhour` (`id`, `doctor_id`, `doctor_name`, `workdate`, `status`) VALUES
	(10, 3, 'Zeynep Nida Mutlu', '2022-52-04 08:30:00', 'A'),
	(11, 3, 'Zeynep Nida Mutlu', '2022-52-04 09:00:00', 'A'),
	(12, 3, 'Zeynep Nida Mutlu', '2022-52-04 09:30:00', 'A'),
	(13, 3, 'Zeynep Nida Mutlu', '2022-52-04 10:00:00', 'A'),
	(14, 3, 'Zeynep Nida Mutlu', '2022-52-04 10:30:00', 'A'),
	(15, 3, 'Zeynep Nida Mutlu', '2022-52-04 11:00:00', 'A'),
	(16, 3, 'Zeynep Nida Mutlu', '2022-52-04 11:30:00', 'A'),
	(17, 3, 'Zeynep Nida Mutlu', '2022-52-04 12:00:00', 'A'),
	(18, 3, 'Zeynep Nida Mutlu', '2022-52-04 12:30:00', 'A'),
	(19, 3, 'Zeynep Nida Mutlu', '2022-52-04 13:30:00', 'A'),
	(20, 3, 'Zeynep Nida Mutlu', '2022-52-04 14:00:00', 'P'),
	(21, 3, 'Zeynep Nida Mutlu', '2022-52-04 14:30:00', 'A'),
	(22, 3, 'Zeynep Nida Mutlu', '2022-52-04 15:00:00', 'A'),
	(23, 3, 'Zeynep Nida Mutlu', '2022-52-04 15:30:00', 'A'),
	(24, 3, 'Zeynep Nida Mutlu', '2022-52-04 16:00:00', 'A'),
	(25, 3, 'Zeynep Nida Mutlu', '2022-52-04 16:30:00', 'A'),
	(26, 3, 'Zeynep Nida Mutlu', '2022-52-04 17:00:00', 'A'),
	(27, 12, 'Hasan Atıcı', '2022-00-04 08:00:00', 'A'),
	(28, 12, 'Hasan Atıcı', '2022-00-04 15:00:00', 'P');
/*!40000 ALTER TABLE `workhour` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
