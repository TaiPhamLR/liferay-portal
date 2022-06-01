package com.liferay.headless.delivery.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;
import com.liferay.wiki.exception.WikiPageRuntimeException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code WikiPageRuntimeException} to a {@code 400} error.
 *
 * @author Tai Pham
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Delivery)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Delivery.WikiPageRuntimeExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class WikiPageRuntimeExceptionMapper
	extends BaseExceptionMapper<WikiPageRuntimeException> {

	@Override
	protected Problem getProblem(
			WikiPageRuntimeException wikiPageRuntimeException) {
		return new Problem(
			Response.Status.BAD_REQUEST, wikiPageRuntimeException.getMessage());
	}

	@Override
	public Response toResponse(
			WikiPageRuntimeException wikiPageRuntimeException) {

		Problem problem = getProblem(wikiPageRuntimeException);

		if (_log.isWarnEnabled()) {
			_log.warn("Problem " + problem, wikiPageRuntimeException);
		}

		Response.Status status = problem.getStatus();

		Response.ResponseBuilder responseBuilder = Response.status(status);

		responseBuilder.entity(problem)
			.type(MediaType.APPLICATION_JSON_TYPE);

		return responseBuilder.build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
			WikiPageRuntimeExceptionMapper.class);
}