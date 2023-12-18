package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UpdaterService {
	void update() throws JsonProcessingException, InterruptedException;
}
