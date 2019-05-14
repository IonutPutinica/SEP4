USE [SEPDimension]
GO

INSERT INTO [dbo].[F_CO2]
           ([DateKey]
           ,[TimeKey]
           ,[PlantKey]
           ,[Units_CO2])
     Select
	 DateKey,
	 TimeKey,
	 PlantKey,
	 Units_CO2
	 from SEP_Staging.dbo.F_CO2_staging
GO


