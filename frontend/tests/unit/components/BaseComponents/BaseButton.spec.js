import BaseButton from "@/components/BaseComponents/BaseButton";
import { mount } from "@vue/test-utils";

describe("BaseButton", () => {
  test("Test click on button", async () => {
    const wrapper = mount(BaseButton);

    await wrapper.trigger("click");

    expect(wrapper.emitted("clicked")).toBeTruthy();
  });
});
