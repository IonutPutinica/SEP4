USE [SEPDimension]
GO

INSERT INTO [dbo].[F_Light]
           ([DateKey]
           ,[TimeKey]
           ,[PlantKey]
           ,[Units_Light])
     Select
	 DateKey,
	 TimeKey,
	 PlantKey,
	 Units_Light
	 from SEP_Staging.dbo.F_Light_Staging
GO


