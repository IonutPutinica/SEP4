USE [SEP_Staging]
GO

UPDATE [dbo].[F_Temperature_Staging]
   SET [TimeKey] = (Select SEPDimension.dbo.D_Time.TimeKey from SEPDimension.dbo.D_Time
   where convert(time,[Time30]) =Time_Value) 
GO


