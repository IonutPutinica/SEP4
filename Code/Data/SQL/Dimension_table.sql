USE [master]
GO
/****** Object:  Database [SEPDimension]    Script Date: 13/05/2019 16:04:36 ******/
CREATE DATABASE [SEPDimension]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SEPDimension', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\SEPDimension.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SEPDimension_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\SEPDimension_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [SEPDimension] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SEPDimension].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SEPDimension] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SEPDimension] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SEPDimension] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SEPDimension] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SEPDimension] SET ARITHABORT OFF 
GO
ALTER DATABASE [SEPDimension] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SEPDimension] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SEPDimension] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SEPDimension] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SEPDimension] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SEPDimension] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SEPDimension] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SEPDimension] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SEPDimension] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SEPDimension] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SEPDimension] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SEPDimension] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SEPDimension] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SEPDimension] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SEPDimension] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SEPDimension] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SEPDimension] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SEPDimension] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SEPDimension] SET  MULTI_USER 
GO
ALTER DATABASE [SEPDimension] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SEPDimension] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SEPDimension] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SEPDimension] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SEPDimension] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SEPDimension] SET QUERY_STORE = OFF
GO
USE [SEPDimension]
GO
/****** Object:  Table [dbo].[D_Date]    Script Date: 13/05/2019 16:04:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[D_Date](
	[dateKey] [int] IDENTITY(1,1) NOT NULL,
	[CalendarDate] [datetime] NULL,
	[Year] [nvarchar](50) NULL,
	[MonthName] [nvarchar](50) NULL,
	[Day] [nvarchar](50) NULL,
 CONSTRAINT [PK_D_Date] PRIMARY KEY CLUSTERED 
(
	[dateKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[D_Plant]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[D_Plant](
	[PlantKey] [int] NOT NULL,
	[B_PlantKey] [int] NULL,
	[PlantName] [nchar](20) NULL,
	[Valid_From] [date] NULL,
	[Valid_To] [date] NULL,
 CONSTRAINT [PK_D_Plant] PRIMARY KEY CLUSTERED 
(
	[PlantKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[D_Time]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[D_Time](
	[TimeKey] [int] IDENTITY(1,1) NOT NULL,
	[TimeAltKey] [int] NOT NULL,
	[Time30] [varchar](8) NOT NULL,
	[Hour30] [tinyint] NOT NULL,
	[MinuteNumber] [tinyint] NOT NULL,
	[SecondNumber] [tinyint] NOT NULL,
	[TimeInSecond] [int] NOT NULL,
	[HourlyBucket] [varchar](15) NOT NULL,
	[DayTimeBucketGroupKey] [int] NOT NULL,
	[DayTimeBucket] [varchar](100) NOT NULL,
 CONSTRAINT [PK_D_Time] PRIMARY KEY CLUSTERED 
(
	[TimeKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[F_CO2]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[F_CO2](
	[DateKey] [int] NULL,
	[TimeKey] [int] NULL,
	[PlantKey] [int] NULL,
	[Units_CO2] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[F_Humidity]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[F_Humidity](
	[DateKey] [int] NULL,
	[TimeKey] [int] NULL,
	[PlantKey] [int] NULL,
	[Units_Humidity] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[F_Light]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[F_Light](
	[DateKey] [int] NULL,
	[TimeKey] [int] NULL,
	[PlantKey] [int] NULL,
	[Units_Light] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[F_Temperature]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[F_Temperature](
	[DateKey] [int] NULL,
	[TimeKey] [int] NULL,
	[PlantKey] [int] NULL,
	[Units_Temperature] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[F_Watering]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[F_Watering](
	[DateKey] [int] NULL,
	[TimeKey] [int] NULL,
	[PlantKey] [int] NULL,
	[AmountOfWater] [float] NULL,
	[HoursSinceWatering] [float] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[F_CO2]  WITH CHECK ADD  CONSTRAINT [FK_F_CO2_D_Date] FOREIGN KEY([DateKey])
REFERENCES [dbo].[D_Date] ([dateKey])
GO
ALTER TABLE [dbo].[F_CO2] CHECK CONSTRAINT [FK_F_CO2_D_Date]
GO
ALTER TABLE [dbo].[F_CO2]  WITH CHECK ADD  CONSTRAINT [FK_F_CO2_D_Plant] FOREIGN KEY([PlantKey])
REFERENCES [dbo].[D_Plant] ([PlantKey])
GO
ALTER TABLE [dbo].[F_CO2] CHECK CONSTRAINT [FK_F_CO2_D_Plant]
GO
ALTER TABLE [dbo].[F_CO2]  WITH CHECK ADD  CONSTRAINT [FK_F_CO2_D_Time] FOREIGN KEY([TimeKey])
REFERENCES [dbo].[D_Time] ([TimeKey])
GO
ALTER TABLE [dbo].[F_CO2] CHECK CONSTRAINT [FK_F_CO2_D_Time]
GO
ALTER TABLE [dbo].[F_Humidity]  WITH CHECK ADD  CONSTRAINT [FK_F_Humidity_D_Date] FOREIGN KEY([DateKey])
REFERENCES [dbo].[D_Date] ([dateKey])
GO
ALTER TABLE [dbo].[F_Humidity] CHECK CONSTRAINT [FK_F_Humidity_D_Date]
GO
ALTER TABLE [dbo].[F_Humidity]  WITH CHECK ADD  CONSTRAINT [FK_F_Humidity_D_Plant] FOREIGN KEY([PlantKey])
REFERENCES [dbo].[D_Plant] ([PlantKey])
GO
ALTER TABLE [dbo].[F_Humidity] CHECK CONSTRAINT [FK_F_Humidity_D_Plant]
GO
ALTER TABLE [dbo].[F_Humidity]  WITH CHECK ADD  CONSTRAINT [FK_F_Humidity_D_Time] FOREIGN KEY([TimeKey])
REFERENCES [dbo].[D_Time] ([TimeKey])
GO
ALTER TABLE [dbo].[F_Humidity] CHECK CONSTRAINT [FK_F_Humidity_D_Time]
GO
ALTER TABLE [dbo].[F_Light]  WITH CHECK ADD  CONSTRAINT [FK_F_Light_D_Date] FOREIGN KEY([DateKey])
REFERENCES [dbo].[D_Date] ([dateKey])
GO
ALTER TABLE [dbo].[F_Light] CHECK CONSTRAINT [FK_F_Light_D_Date]
GO
ALTER TABLE [dbo].[F_Light]  WITH CHECK ADD  CONSTRAINT [FK_F_Light_D_Plant] FOREIGN KEY([PlantKey])
REFERENCES [dbo].[D_Plant] ([PlantKey])
GO
ALTER TABLE [dbo].[F_Light] CHECK CONSTRAINT [FK_F_Light_D_Plant]
GO
ALTER TABLE [dbo].[F_Light]  WITH CHECK ADD  CONSTRAINT [FK_F_Light_D_Time] FOREIGN KEY([TimeKey])
REFERENCES [dbo].[D_Time] ([TimeKey])
GO
ALTER TABLE [dbo].[F_Light] CHECK CONSTRAINT [FK_F_Light_D_Time]
GO
ALTER TABLE [dbo].[F_Temperature]  WITH CHECK ADD  CONSTRAINT [FK_F_Temperature_D_Date] FOREIGN KEY([DateKey])
REFERENCES [dbo].[D_Date] ([dateKey])
GO
ALTER TABLE [dbo].[F_Temperature] CHECK CONSTRAINT [FK_F_Temperature_D_Date]
GO
ALTER TABLE [dbo].[F_Temperature]  WITH CHECK ADD  CONSTRAINT [FK_F_Temperature_D_Time] FOREIGN KEY([TimeKey])
REFERENCES [dbo].[D_Time] ([TimeKey])
GO
ALTER TABLE [dbo].[F_Temperature] CHECK CONSTRAINT [FK_F_Temperature_D_Time]
GO
ALTER TABLE [dbo].[F_Watering]  WITH CHECK ADD  CONSTRAINT [FK_F_Watering_D_Date] FOREIGN KEY([DateKey])
REFERENCES [dbo].[D_Date] ([dateKey])
GO
ALTER TABLE [dbo].[F_Watering] CHECK CONSTRAINT [FK_F_Watering_D_Date]
GO
ALTER TABLE [dbo].[F_Watering]  WITH CHECK ADD  CONSTRAINT [FK_F_Watering_D_Plant] FOREIGN KEY([PlantKey])
REFERENCES [dbo].[D_Plant] ([PlantKey])
GO
ALTER TABLE [dbo].[F_Watering] CHECK CONSTRAINT [FK_F_Watering_D_Plant]
GO
ALTER TABLE [dbo].[F_Watering]  WITH CHECK ADD  CONSTRAINT [FK_F_Watering_D_Time] FOREIGN KEY([TimeKey])
REFERENCES [dbo].[D_Time] ([TimeKey])
GO
ALTER TABLE [dbo].[F_Watering] CHECK CONSTRAINT [FK_F_Watering_D_Time]
GO
/****** Object:  StoredProcedure [dbo].[FillDimTime]    Script Date: 13/05/2019 16:04:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[FillDimTime] 
as 
BEGIN 
--Specify Total Number of Hours You need to fill in Time Dimension 
DECLARE @Size INTEGER 
--iF @Size=32 THEN This will Fill values Upto 32:59 hr in Time Dimension 
Set @Size=23 
DECLARE @hour INTEGER 
DECLARE @minute INTEGER 
DECLARE @second INTEGER 
DECLARE @TimeAltKey INTEGER 
DECLARE @TimeInSeconds INTEGER 
DECLARE @Time30 varchar(25) 
DECLARE @Hour30 varchar(4) 
DECLARE @Minute30 varchar(4) 
DECLARE @Second30 varchar(4) 
DECLARE @HourBucket varchar(15) 
DECLARE @HourBucketGroupKey int 
DECLARE @DayTimeBucket varchar(100) 
DECLARE @DayTimeBucketGroupKey int 
SET @hour = 0 
SET @minute = 0 
SET @second = 0 
SET @TimeAltKey = 0 
WHILE(@hour<= @Size ) 
BEGIN 
if (@hour <10 ) 
begin 
set @Hour30 = '0' + cast( @hour as varchar(10)) 
end 
else 
begin 
set @Hour30 = @hour 
end 
--Create Hour Bucket Value 
set @HourBucket= @Hour30+':00' +'-' +@Hour30+':59' 
WHILE(@minute <= 59) 
BEGIN 
WHILE(@second <= 59) 
BEGIN 
set @TimeAltKey = @hour *10000 +@minute*100 +@second 
set @TimeInSeconds =@hour * 3600 + @minute *60 +@second 
If @minute <10 
begin 
set @Minute30 = '0' + cast ( @minute as varchar(10) ) 
end 
else 
begin 
set @Minute30 = @minute 
end 
if @second <10 
begin 
set @Second30 = '0' + cast ( @second as varchar(10) ) 
end 
else 
begin 
set @Second30 = @second 
end 
--Concatenate values for Time30 
set @Time30 = @Hour30 +':'+@Minute30 +':'+@Second30 
--DayTimeBucketGroupKey can be used in Sorting of DayTime Bucket In proper Order 
SELECT @DayTimeBucketGroupKey = 
CASE 
WHEN (@TimeAltKey >= 00000 AND @TimeAltKey <= 25959) THEN 0 
WHEN (@TimeAltKey >= 30000 AND @TimeAltKey <= 65959) THEN 1 
WHEN (@TimeAltKey >= 70000 AND @TimeAltKey <= 85959) THEN 2 
WHEN (@TimeAltKey >= 90000 AND @TimeAltKey <= 115959) THEN 3 
WHEN (@TimeAltKey >= 120000 AND @TimeAltKey <= 135959)THEN 4 
WHEN (@TimeAltKey >= 140000 AND @TimeAltKey <= 155959)THEN 5 
WHEN (@TimeAltKey >= 50000 AND @TimeAltKey <= 175959) THEN 6 
WHEN (@TimeAltKey >= 180000 AND @TimeAltKey <= 235959)THEN 7 
WHEN (@TimeAltKey >= 240000) THEN 8 
END 
--print @DayTimeBucketGroupKey 
-- DayTimeBucket Time Divided in Specific Time Zone 
-- So Data can Be Grouped as per Bucket for Analyzing as per time of day 
SELECT @DayTimeBucket = 
CASE 
WHEN (@TimeAltKey >= 00000 AND @TimeAltKey <= 25959) 
THEN 'Late Night (00:00 AM To 02:59 AM)' 
WHEN (@TimeAltKey >= 30000 AND @TimeAltKey <= 65959) 
THEN 'Early Morning(03:00 AM To 6:59 AM)' 
WHEN (@TimeAltKey >= 70000 AND @TimeAltKey <= 85959) 
THEN 'AM Peak (7:00 AM To 8:59 AM)' 
WHEN (@TimeAltKey >= 90000 AND @TimeAltKey <= 115959) 
THEN 'Mid Morning (9:00 AM To 11:59 AM)' 
WHEN (@TimeAltKey >= 120000 AND @TimeAltKey <= 135959) 
THEN 'Lunch (12:00 PM To 13:59 PM)' 
WHEN (@TimeAltKey >= 140000 AND @TimeAltKey <= 155959)
THEN 'Mid Afternoon (14:00 PM To 15:59 PM)' 
WHEN (@TimeAltKey >= 50000 AND @TimeAltKey <= 175959)
THEN 'PM Peak (16:00 PM To 17:59 PM)' 
WHEN (@TimeAltKey >= 180000 AND @TimeAltKey <= 235959)
THEN 'Evening (18:00 PM To 23:59 PM)' 
WHEN (@TimeAltKey >= 240000) THEN 'Previous Day Late Night _
(24:00 PM to '+cast( @Size as varchar(10)) +':00 PM )' 
END 
-- print @DayTimeBucket 
INSERT into D_Time(TimeAltKey,[Time30] ,[Hour30] ,
[MinuteNumber],[SecondNumber],[TimeInSecond],[HourlyBucket],
DayTimeBucketGroupKey,DayTimeBucket) 
VALUES (@TimeAltKey ,@Time30 ,@hour ,@minute,@Second , 
@TimeInSeconds,@HourBucket,@DayTimeBucketGroupKey,@DayTimeBucket ) 
SET @second = @second + 1 
END 
SET @minute = @minute + 1 
SET @second = 0 
END 
SET @hour = @hour + 1 
SET @minute =0 
END 
END 

GO
USE [master]
GO
ALTER DATABASE [SEPDimension] SET  READ_WRITE 
GO
