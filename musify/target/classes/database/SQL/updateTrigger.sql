Create Trigger playlist_updated_at_trigger Before Update on musify.playlists for each row
Set new.last_updated_date = now();