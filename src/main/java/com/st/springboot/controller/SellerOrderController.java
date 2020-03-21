package com.st.springboot.controller;

import com.st.springboot.dataobject.OrderMaster;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.repository.OrderMasterRepository;
import com.st.springboot.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author shaotian
 * @create 2020-03-14 13:09
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 订单列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView List(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {

        PageRequest of = PageRequest.of(page - 1, size);
        Page<OrderMasterDto> orderMaster = orderService.findList(of);
        map.put("orderMaster", orderMaster);
        map.put("size", size);
        map.put("currentPage", page);

        return new ModelAndView("order/list", map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderMasterDto orderMasterDto = orderService.findOne(orderId);
            orderService.cancel(orderMasterDto);
        } catch (Exception e) {
            log.error("[订单不存在]", e);
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnums.ORDER_ERROR.getMssage());
        map.put("url", "http://sellst.natapp1.cc/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {

        OrderMasterDto orderMasterDto = new OrderMasterDto();
        try {
            orderMasterDto = orderService.findOne(orderId);
        } catch (Exception e) {
            log.error("[订单详情不存在]", e);
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderMasterDto", orderMasterDto);
        return new ModelAndView("order/detail", map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {

        OrderMasterDto orderMasterDto = new OrderMasterDto();
        try {
            orderMasterDto = orderService.findOne(orderId);
            orderService.finish(orderMasterDto);
        } catch (Exception e) {
            log.error("[订单详情不存在]", e);
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnums.ORDER_FINISH.getMssage());
        map.put("url", "http://sellst.natapp1.cc/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }

    /**
     * 搜索订单
     *
     * @param buyname
     * @param map
     * @return
     */
    @GetMapping("/searchbuyName")
    public ModelAndView searchbuyName(@RequestParam("buyname") String buyname,
                                      Map<String, Object> map) {
        PageRequest of = PageRequest.of(0, 5);
        Page<OrderMaster> orderMaster = orderMasterRepository.findByBuyerNameLike(buyname, of);
        map.put("orderMaster", orderMaster);

        return new ModelAndView("order/list", map);
    }

}
