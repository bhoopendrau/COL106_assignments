from dis import dis


class Service:
    def __init__(self, service_id, model, manufacturer, distance_travelled, is_insured, minimum_payment):
        self.service_id = service_id
        self.model = model
        self.manufacturer = manufacturer
        self.distance_travelled = distance_travelled
        self.is_insured = is_insured
        self.minimum_payment = 0

class ServiceCenter:
    list = []
    dict = {}
    def __init__(self, list, dict):
        self.dict = dict
        length = len(list)
        i=0
        while i < length:
            if list[i].is_insured=='yes':
                list[i].minimum_payment = 1000
            else:
                list[i].minimum_payment = 1750
            i+=1
        self.list = list
    
    def return_cars(self,state_code):
        list_cars_in_state = []
        