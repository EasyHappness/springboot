package cn.no7player.config;

import org.springframework.dao.DataAccessException;

public class UserException extends DataAccessException{

	public UserException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = -3239088688072579496L;

}
