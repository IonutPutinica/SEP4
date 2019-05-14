USE [SEPDimension]
GO

INSERT INTO [dbo].[F_Watering]
           ([DateKey]
           ,[TimeKey]
           ,[PlantKey]
           ,[AmountOfWater]
           ,[HoursSinceWatering])
    Select
	DateKey,
	TimeKey,
	PlantKey,
	AmountOfWater,
	HoursSinceWatering
	from SEP_Staging.dbo.F_Watering_staging
GO


