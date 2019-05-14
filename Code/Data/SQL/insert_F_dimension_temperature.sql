USE [SEPDimension]
GO

INSERT INTO [dbo].[F_Temperature]
           ([DateKey]
           ,[TimeKey]
           ,[PlantKey]
           ,[Units_Temperature])
     Select DateKey,
	 TimeKey,
	 PlantKey,
	 Units_Temperature
	 from SEP_Staging.dbo.F_Temperature_Staging
GO


