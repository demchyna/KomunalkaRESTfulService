package com.mdem.komunalka.service.impl;

import com.mdem.komunalka.model.Unit;
import com.mdem.komunalka.service.common.AbstractService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UnitService extends AbstractService<Unit, Long> {
}
