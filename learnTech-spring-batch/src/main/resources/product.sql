
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `PRODUCT`;
CREATE TABLE `PRODUCT` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(128) NOT NULL,
  `DESCRIPTION` varchar(128) default NULL,
  `QUANTITY` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
