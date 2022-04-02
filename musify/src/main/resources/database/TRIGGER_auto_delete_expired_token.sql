DELIMITER $$
drop Trigger if exists auto_delete_token_when_updated ;
Create Trigger auto_delete_token_when_updated
  BEFORE insert ON `musify`.`tokens`
  FOR EACH ROW 
  BEGIN

  DELETE FROM `musify`.`tokens`
  WHERE expiry_date <= NOW();

END$$ 
DELIMITER ;