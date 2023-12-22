
import { UsersContainer } from './container-component.style';

export const SampleContainer = (props) => {
    return (
        <UsersContainer maxWidth="md" sx={props.sx}>{props.children}</UsersContainer>
    );
}
