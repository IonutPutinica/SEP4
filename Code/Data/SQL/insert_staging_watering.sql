USE [SEP_Staging]
GO

INSERT INTO [dbo].[F_Watering_Staging]
           (
		   [B_PlantKey],
           [AmountOfWater]
		   ,[HoursSinceWatering]
           ,[Date_Value]
		   ,[Time_Value]
           )
     Select
	 PlantID,
	 AmountOfWater,
	 HoursSinceWatering,
convert(date, [ReadingDate]) as [Date],
convert(time,[ReadingDate]) as [Time]
  from [SEP].[dbo].Readings


GO


