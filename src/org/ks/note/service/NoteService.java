package org.ks.note.service;

import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.util.NoteResult;

public interface NoteService {
	public NoteResult loadNotes(String bookid);
	public NoteResult loadBody(String noteid);
	public NoteResult add(String bookid,String userid,String title);
	public NoteResult update(String noteid,String title,String body);
	public NoteResult updateStatus(String noteid);
	public NoteResult moveNote(String noteid,String bookid);
	public NoteResult loadDisableNotes(String userid);
	public NoteResult delete(String noteid);
	public NoteResult replay(String bookid,String noteid);
	public Share findByNoteIdFromShare(String noteid);
	public NoteResult addLike(String noteid,String userid);
	public NoteResult findByUserid(String userid);
}
