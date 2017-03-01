package com.nkl.page.action;

import com.nkl.common.action.BaseDownloadAction;
import com.nkl.common.util.FindProjectPath;
import com.nkl.page.domain.Music;
import com.nkl.page.manager.IndexManager;

@SuppressWarnings("serial")
public class DownLoadAction extends BaseDownloadAction {
	IndexManager indexManager = new IndexManager();
	
	Music paramsMusic;
	
	public Music getParamsMusic() {
		return paramsMusic;
	}

	public void setParamsMusic(Music paramsMusic) {
		this.paramsMusic = paramsMusic;
	}

	/**
	 * 文件下载
	 * @return
	 */
	public String execute() {
		try {
			Music music = indexManager.queryMusic(paramsMusic);
			setFileName(music.getMusic_name());
			setFilePath(FindProjectPath.getRootPath(getInputPath()+"/"+music.getMusic_name2()));
			//更新下载次数
			indexManager.updateMusicDown(music);
		
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return SUCCESS;
	}
}