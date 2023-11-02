
import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { styled } from '@mui/material';



export const SampleCard = (props) => {
    const { id, name, email, age } = props.user;
    return (
        <Card sx={{ minWidth: 275 }}>
            <CardContent>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                    ID: {id}
                </Typography>
                <Typography variant="h5" component="div">
                    Name: {name}
                </Typography>
                <Typography color="text.secondary">
                    Email: {email}
                </Typography>
            </CardContent>
        </Card>
    );
}
export const UserCard = styled(SampleCard)({
    padding: 8,
    margin: 8,
    borderRadius: "10px"
})