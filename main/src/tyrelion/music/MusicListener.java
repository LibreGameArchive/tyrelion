/**
 * 
 */
package tyrelion.music;

import org.newdawn.slick.Music;

/**
 * @author jahudi
 * Implementation of the Slick MusicListener interface. Its purpose is to play the next track if the
 * actual track ended.
 */
public class MusicListener implements org.newdawn.slick.MusicListener {

	/**
	 * Will cast the refering Music as TyrelionMusic and start the playback of the next
	 * track of its category.
	 * @see org.newdawn.slick.MusicListener#musicEnded(org.newdawn.slick.Music)
	 */
	@Override
	public void musicEnded(Music music) {
		
		TyrelionMusic tMusic = (TyrelionMusic) music;
		MusicManager.getInstance().loop(tMusic.getCategory());

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.MusicListener#musicSwapped(org.newdawn.slick.Music, org.newdawn.slick.Music)
	 */
	@Override
	public void musicSwapped(Music music, Music newMusic) {
		// TODO Auto-generated method stub

	}

}
