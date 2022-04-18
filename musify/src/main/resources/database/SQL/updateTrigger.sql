create Trigger playlist_last_updated_date_trigger Before Update On musify.playlists for each row 
set last_updated_date = now();
