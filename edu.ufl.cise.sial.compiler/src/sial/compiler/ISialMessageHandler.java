package sial.compiler;

import lpg.runtime.IMessageHandler;

public interface ISialMessageHandler extends IMessageHandler {
	void handleFileNotFoundMessage(String msg);
}
