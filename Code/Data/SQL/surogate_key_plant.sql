USE [SEP_Staging]
GO

UPDATE [dbo].[F_Temperature_Staging]
   SET [PlantKey] = (Select PlantKey from Plant_Dimension_Staging 
   where Plant_Dimension_Staging.B_PlantKey = F_Temperature_Staging.B_PlantKey)
      
GO



