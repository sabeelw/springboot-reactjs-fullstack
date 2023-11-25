import FormControl from '@mui/material/FormControl';
import FormHelperText from '@mui/material/FormHelperText';
import Input from '@mui/material/Input';
import InputLabel from '@mui/material/InputLabel';
import OutlinedInput from '@mui/material/OutlinedInput';
import { BoxContainer as Box } from './form-component.style';
import { ButtonMain } from '../button-component/button-component';
import { useState } from 'react';


export const Form = (props) => {

    return (
        <form>
            <Box>
                <FormControl>
                    <InputLabel htmlFor="component-outlined">Name:</InputLabel>
                    <OutlinedInput
                        id="component-outlined"
                        label="Name"
                        name='Name'
                        onChange={props.onChangeHandler}
                    />
                </FormControl>
                <FormControl>
                    <InputLabel htmlFor="component-outlined">Email:</InputLabel>
                    <OutlinedInput
                        id="component-outlined"
                        label="Email"
                        name='Email'
                        onChange={props.onChangeHandler}
                    />
                </FormControl>
                <FormControl>
                    <InputLabel htmlFor="component-outlined">Age:</InputLabel>
                    <OutlinedInput
                        id="component-outlined"
                        label="Age"
                        name='Age'
                        onChange={props.onChangeHandler}
                    />
                </FormControl>
                <ButtonMain type={"submit"} onClick={props.onClick}>Register</ButtonMain>
            </Box>
        </form>
    )
}