USE [SEPDimension]
GO

INSERT INTO [dbo].[F_Humidity]
           ([DateKey]
           ,[TimeKey]
           ,[PlantKey]
           ,[Units_Humidity])
    Select
	DateKey,
	TimeKey,
	PlantKey,
	Units_Humidity
	from SEP_Staging.dbo.F_Humidity_Staging
GO


