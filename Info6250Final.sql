-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 14, 2017 at 10:20 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Info6250Final`
--

-- --------------------------------------------------------

--
-- Table structure for table `bid_record`
--

CREATE TABLE `bid_record` (
  `bid_id` int(11) NOT NULL,
  `bid_desc` varchar(500) NOT NULL,
  `bid_status` varchar(20) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `bid_amount` float NOT NULL,
  `service_id` int(11) NOT NULL,
  `startup_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bid_record`
--

INSERT INTO `bid_record` (`bid_id`, `bid_desc`, `bid_status`, `start_date`, `end_date`, `bid_amount`, `service_id`, `startup_id`) VALUES
(1, 'bidTestDesc', 'done', '1111-11-21 19:00:00', '1000-11-30 19:00:00', 1000, 1, 1),
(2, 'deleteBidtest', 'accept', '1111-11-21 19:00:00', '1000-11-30 19:00:00', 1000, 2, 1),
(3, 'updateBidtest', 'active', '1111-11-21 19:00:00', '1000-11-30 19:00:00', 1000, 3, 1),
(4, 'Bidtestupdate', 'decline', '1111-11-21 19:00:00', '1000-11-30 19:00:00', 2000, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL,
  `category_desc` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`, `category_desc`) VALUES
(1, 'Music', 'music');

-- --------------------------------------------------------

--
-- Table structure for table `completion_record`
--

CREATE TABLE `completion_record` (
  `record_id` int(11) NOT NULL,
  `percentage` varchar(10) NOT NULL,
  `update_date` datetime NOT NULL,
  `service_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `completion_record`
--

INSERT INTO `completion_record` (`record_id`, `percentage`, `update_date`, `service_id`) VALUES
(1, '20%', '2012-01-01 19:00:00', 2),
(2, '40%', '2013-04-20 20:00:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `idea`
--

CREATE TABLE `idea` (
  `idea_id` int(11) NOT NULL,
  `idea_name` varchar(20) NOT NULL,
  `idea_desc` varchar(400) NOT NULL,
  `target_amount` float NOT NULL,
  `gathered_amount` float NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `idea_status` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `idea`
--

INSERT INTO `idea` (`idea_id`, `idea_name`, `idea_desc`, `target_amount`, `gathered_amount`, `start_date`, `end_date`, `idea_status`, `user_id`, `category_id`) VALUES
(1, 'ArtIdea', 'idea art', 5000, 510, '2012-11-21 19:00:00', '2013-01-30 19:00:00', 'Open', 3, 1),
(2, 'MusicIdea', 'idea music', 4000, 0, '2013-11-21 19:00:00', '2014-01-30 19:00:00', 'Open', 3, 1),
(3, 'testuser', 'rrrr', 4444, 0, '2011-01-21 19:00:00', '2012-01-21 19:00:00', 'Open', 3, 1),
(4, 'rrr', 'rrrr', 5000, 0, '2011-01-21 19:00:00', '2012-01-21 19:00:00', 'Open', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `idea_option`
--

CREATE TABLE `idea_option` (
  `option_id` int(11) NOT NULL,
  `option_desc` varchar(200) DEFAULT NULL,
  `option_price` float NOT NULL DEFAULT '0',
  `max_funding` float NOT NULL,
  `bought_amount` int(11) NOT NULL,
  `idea_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `idea_option`
--

INSERT INTO `idea_option` (`option_id`, `option_desc`, `option_price`, `max_funding`, `bought_amount`, `idea_id`) VALUES
(1, 'testoption', 100, 4000, 0, 1),
(2, 'logtest', 200, 3000, 2, 1),
(3, 'ttt', 55, 555, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payinfo`
--

CREATE TABLE `payinfo` (
  `payinfo_id` int(11) NOT NULL,
  `card_no` varchar(300) NOT NULL,
  `cvv` varchar(300) NOT NULL,
  `exp_date` varchar(300) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payinfo`
--

INSERT INTO `payinfo` (`payinfo_id`, `card_no`, `cvv`, `exp_date`, `user_id`) VALUES
(1, 'ea8904a0de19754227a33e2bf3c8cc380fb91fd2c9527b91ca45b3b854e27362', '6183bb79a9f3acdf34cd1a8c029797f5f52f54935ea2315285425f19de541734', '455473bb653666fcac550a5e00453a10783c44720a70e08e7f44d8b7498d522f', 6);

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL,
  `purchase_amount` int(11) NOT NULL,
  `purchase_date` datetime NOT NULL,
  `purchase_status` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`purchase_id`, `purchase_amount`, `purchase_date`, `purchase_status`, `user_id`, `option_id`) VALUES
(1, 2, '2017-12-11 22:38:54', 'Order Placed', 6, 1),
(2, 0, '2017-12-11 23:07:28', 'Order Placed', 6, 1),
(3, 0, '2017-12-14 03:39:53', 'Order Placed', 6, 1),
(4, 2, '2017-12-14 04:04:12', 'Order Placed', 6, 3),
(5, 1, '2017-12-14 04:09:42', 'Order Placed', 6, 2),
(6, 1, '2017-12-14 04:12:00', 'Order Placed', 6, 2);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  `role_desc` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `role_desc`) VALUES
(1, 'Admin', 'admin'),
(2, 'Creator', 'creator'),
(3, 'Startup', 'startup'),
(4, 'Guest', 'guest'),
(5, 'Funder', 'funder');

-- --------------------------------------------------------

--
-- Table structure for table `shopping_cart`
--

CREATE TABLE `shopping_cart` (
  `shopping_cart_id` int(11) NOT NULL,
  `added_on_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `startup`
--

CREATE TABLE `startup` (
  `startup_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `startup`
--

INSERT INTO `startup` (`startup_id`, `category_id`, `user_id`) VALUES
(1, 1, 4),
(2, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `startup_service`
--

CREATE TABLE `startup_service` (
  `service_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `base_amount` float NOT NULL,
  `end_date` datetime NOT NULL,
  `service_desc` varchar(400) DEFAULT NULL,
  `service_status` varchar(20) DEFAULT NULL,
  `idea_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `startup_service`
--

INSERT INTO `startup_service` (`service_id`, `start_date`, `base_amount`, `end_date`, `service_desc`, `service_status`, `idea_id`) VALUES
(1, '2011-01-21 19:00:00', 2000, '1111-11-10 19:00:00', 'servicetest', 'done', 1),
(2, '2011-01-21 19:00:00', 3000, '2011-12-19 19:00:00', 'deletetest', 'in progress', 1),
(3, '2011-01-21 19:00:00', 5000, '1111-11-10 19:00:00', 'updatetest', 'active', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `your_name` varchar(20) NOT NULL,
  `user_desc` varchar(100) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role_id` int(11) NOT NULL,
  `user_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `your_name`, `user_desc`, `username`, `password`, `role_id`, `user_status`) VALUES
(1, 'administrator', 'administrator', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 1, 'active'),
(2, 'aaa', 'aaa', 'ttt', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 1, 'active'),
(3, 'ccc', 'ccc', 'ccc', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 2, 'active'),
(4, 'sss', 'sss', 'sss', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 3, 'active'),
(5, 'cc', 'cc', 'cc', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 2, 'active'),
(6, 'fff', 'fff', 'fff', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 5, 'active'),
(7, 'testnew', 'testnew', 'testnew', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 4, 'active'),
(8, 'zzz', 'zzz', 'zzz', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 4, 'active'),
(9, 'qqq', 'qqq', 'qqq', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 5, 'active'),
(10, 'ss', 'ss', 'ss', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 3, 'active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bid_record`
--
ALTER TABLE `bid_record`
  ADD PRIMARY KEY (`bid_id`),
  ADD KEY `IX_Relationship28` (`startup_id`),
  ADD KEY `IX_Relationship8` (`service_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `completion_record`
--
ALTER TABLE `completion_record`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `IX_Relationship31` (`service_id`);

--
-- Indexes for table `idea`
--
ALTER TABLE `idea`
  ADD PRIMARY KEY (`idea_id`),
  ADD KEY `IX_Relationship24` (`category_id`),
  ADD KEY `IX_Relationship17` (`user_id`);

--
-- Indexes for table `idea_option`
--
ALTER TABLE `idea_option`
  ADD PRIMARY KEY (`option_id`),
  ADD KEY `IX_Relationship9` (`idea_id`);

--
-- Indexes for table `payinfo`
--
ALTER TABLE `payinfo`
  ADD PRIMARY KEY (`payinfo_id`),
  ADD KEY `IX_Relationship30` (`user_id`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`purchase_id`),
  ADD KEY `IX_Relationship15` (`option_id`),
  ADD KEY `IX_Relationship22` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `role_name` (`role_name`);

--
-- Indexes for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`shopping_cart_id`),
  ADD KEY `IX_Relationship18` (`user_id`),
  ADD KEY `IX_Relationship26` (`option_id`);

--
-- Indexes for table `startup`
--
ALTER TABLE `startup`
  ADD PRIMARY KEY (`startup_id`),
  ADD KEY `IX_Relationship27` (`category_id`),
  ADD KEY `IX_Relationship29` (`user_id`);

--
-- Indexes for table `startup_service`
--
ALTER TABLE `startup_service`
  ADD PRIMARY KEY (`service_id`),
  ADD KEY `IX_Relationship25` (`idea_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `administrator_username` (`username`),
  ADD KEY `IX_Relationship16` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bid_record`
--
ALTER TABLE `bid_record`
  MODIFY `bid_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `completion_record`
--
ALTER TABLE `completion_record`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `idea`
--
ALTER TABLE `idea`
  MODIFY `idea_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `idea_option`
--
ALTER TABLE `idea_option`
  MODIFY `option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `payinfo`
--
ALTER TABLE `payinfo`
  MODIFY `payinfo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `purchase_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  MODIFY `shopping_cart_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `startup`
--
ALTER TABLE `startup`
  MODIFY `startup_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `startup_service`
--
ALTER TABLE `startup_service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bid_record`
--
ALTER TABLE `bid_record`
  ADD CONSTRAINT `Relationship28` FOREIGN KEY (`startup_id`) REFERENCES `startup` (`startup_id`),
  ADD CONSTRAINT `Relationship8` FOREIGN KEY (`service_id`) REFERENCES `startup_service` (`service_id`);

--
-- Constraints for table `completion_record`
--
ALTER TABLE `completion_record`
  ADD CONSTRAINT `Relationship31` FOREIGN KEY (`service_id`) REFERENCES `startup_service` (`service_id`);

--
-- Constraints for table `idea`
--
ALTER TABLE `idea`
  ADD CONSTRAINT `Relationship17` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `Relationship24` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `idea_option`
--
ALTER TABLE `idea_option`
  ADD CONSTRAINT `Relationship9` FOREIGN KEY (`idea_id`) REFERENCES `idea` (`idea_id`);

--
-- Constraints for table `payinfo`
--
ALTER TABLE `payinfo`
  ADD CONSTRAINT `Relationship30` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `Relationship15` FOREIGN KEY (`option_id`) REFERENCES `idea_option` (`option_id`),
  ADD CONSTRAINT `Relationship22` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD CONSTRAINT `Relationship18` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `Relationship26` FOREIGN KEY (`option_id`) REFERENCES `idea_option` (`option_id`);

--
-- Constraints for table `startup`
--
ALTER TABLE `startup`
  ADD CONSTRAINT `Relationship27` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `Relationship29` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `startup_service`
--
ALTER TABLE `startup_service`
  ADD CONSTRAINT `Relationship25` FOREIGN KEY (`idea_id`) REFERENCES `idea` (`idea_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `Relationship16` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
