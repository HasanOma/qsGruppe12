import UserInQueue from "@/components/BaseComponents/UserInQueue";
import { mount } from "@vue/test-utils";

describe("UserInQueue", () => {
    test("Check that prop is correct", () => {
        const wrapper = mount(UserInQueue, {
            propsData: {
                person: {
                    fullName: "Per Askeladden",
                    room: "A4-112",
                    spot: "1",
                    workNr: "Øving 4",
                    workType: "Godkjenning",
                    message: "Test"
                }
            }
        })
        
        expect(wrapper.props('person')).toEqual({
            fullName: "Per Askeladden",
            room: "A4-112",
            spot: "1",
            workNr: "Øving 4",
            workType: "Godkjenning",
            message: "Test"
        })
    })
})