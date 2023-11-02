import * as React from 'react';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import { useThemeProps } from '@mui/material';

export const ButtonMain = (props) => {
    return (
        <Button type={props.type} variant="contained" onClick={props.onClick}>{props.children}</Button>
    );
}