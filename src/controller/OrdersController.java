package controller;

import model.OrdersModel;
import model.UserHomeModel;
import view.OrdersForm;
import view.UserHome;

public class OrdersController {

    private OrdersModel m_model;
    private OrdersForm m_view;

    public OrdersController(OrdersModel model,OrdersForm view) {
        m_model = model;
        m_view = view;
    }
}
