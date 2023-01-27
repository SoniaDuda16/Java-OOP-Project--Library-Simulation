package controller;

import model.OrdersModel;
import model.UserOrdersModel;
import view.OrdersForm;
import view.UserOrdersForm;

public class UserOrdersController {
    private UserOrdersModel m_model;
    private UserOrdersForm m_view;

    public UserOrdersController(UserOrdersModel model,UserOrdersForm view) {
        m_model = model;
        m_view = view;
    }
}
