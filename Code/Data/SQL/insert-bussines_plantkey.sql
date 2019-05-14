USE [SEP_Staging]
GO

INSERT INTO [dbo].[F_Temperature_Staging]
(
           [B_PlantKey])
     Select 
	 B_Plantkey
	 from SEP_Staging.dbo.Plant_Dimension_Staging      
GO


