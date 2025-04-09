package com.doinglab.sangsik.api.domains.cgm.controller

import com.doinglab.sangsik.api.domains.cgm.service.CgmService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping(value = ["/cgm"])
class CgmViewController(
    private val cgmService: CgmService
) {
    @GetMapping("/connect")
    fun doGetCgmConnect(
        @RequestParam("code") code: String,
        @RequestParam("state") state: String
    ): String =
        cgmService.doGetCgmConnect(code, state)
}
