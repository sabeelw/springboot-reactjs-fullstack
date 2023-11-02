import { createContext } from "react";

export const FormContext = createContext("");

export const FormContextProvider = (props) => {
    return (
        <FormContext.Provider>
            {props.children}
        </FormContext.Provider>
    )
}