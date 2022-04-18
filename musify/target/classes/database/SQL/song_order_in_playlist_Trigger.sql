DELIMITER //

drop trigger if exists  song_order_in_playlist_trigger; //
create Trigger song_order_in_playlist_trigger Before Update On musify.songs_playlists for each row 
begin
  declare order_in_playlist int default 0;
  select COUNT(*) into order_in_playlist from  musify.songs_playlists where playlist_id = new.playlist_id;
set new.order_in_playlist = order_in_playlist+1;
end; //
Delimiter ;